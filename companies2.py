import os
import csv

def slug_to_title(slug: str) -> str:
    return ' '.join(word.capitalize() for word in slug.strip('/').split('-'))

def extract_question_title(link: str) -> str:
    slug = link.strip('/').split('/')[-1]
    return slug_to_title(slug)

def parse_md_for_links_and_solutions(md_path):
    """Extract all unique problems from one markdown file, keyed by LeetCode link."""
    results = {}
    with open(md_path, "r", encoding="utf-8") as f:
        for line in f:
            if "leetcode.com/problems/" in line and "[Solution]" in line:
                # Extract LeetCode link
                start_link = line.find("https://leetcode.com/problems/")
                end_link = line.find(")", start_link)
                leetcode_link = line[start_link:end_link].strip()

                # Extract Solution link
                start_sol = line.find("[Solution](") + len("[Solution](")
                end_sol = line.find(")", start_sol)
                solution_link = line[start_sol:end_sol].strip()

                # Keep only the first solution we encounter for each problem
                if leetcode_link not in results:
                    results[leetcode_link] = solution_link
    return results


def find_delete_and_append(leetcode_csv_dir, markdown_dir, link_to_find, solution_file_path):
    link_to_find = link_to_find.strip()
    companies_found = set()
    matched_csv_data = {}  # {company: (difficulty, topics)}

    for company in os.listdir(leetcode_csv_dir):
        company_path = os.path.join(leetcode_csv_dir, company)
        if not os.path.isdir(company_path):
            continue

        link_found = False

        for filename in os.listdir(company_path):
            if not filename.endswith(".csv"):
                continue

            file_path = os.path.join(company_path, filename)
            updated_rows = []

            try:
                with open(file_path, newline='', encoding='utf-8') as csvfile:
                    reader = csv.DictReader(csvfile)
                    rows = list(reader)

                    for row in rows:
                        if row['Link'].strip() == link_to_find:
                            link_found = True
                            # Store metadata if not already set for this company
                            if company not in matched_csv_data:
                                difficulty = row.get('Difficulty', '').strip()
                                topics = row.get('Topics', '').strip()
                                matched_csv_data[company] = (difficulty, topics)
                        else:
                            updated_rows.append(row)

                if link_found:
                    with open(file_path, mode='w', newline='', encoding='utf-8') as csvfile:
                        writer = csv.DictWriter(csvfile, fieldnames=reader.fieldnames)
                        writer.writeheader()
                        writer.writerows(updated_rows)

            except Exception as e:
                print(f"Error processing file {file_path}: {e}")

        if link_found:
            companies_found.add(company)
            md_path = os.path.join(markdown_dir, f"{company}.md")
            title = extract_question_title(link_to_find)
            difficulty, topics = matched_csv_data.get(company, ("", ""))

            # New markdown row
            new_row = (
                "| {row_number} | "
                f"{difficulty or ''} | "
                f"[{title}]({link_to_find}) | "
                f"[Solution]({solution_file_path}) | "
                f"{topics or ''} |"
            )

            # Create markdown file if not exists
            if not os.path.exists(md_path):
                with open(md_path, "w", encoding="utf-8") as f:
                    f.write(f"# {company}\n\n")
                    f.write("| # | Difficulty | Problem | Solution | Topic |\n")
                    f.write("|---|------------|---------|----------|--------|\n")

            # Read and check for duplicate
            with open(md_path, "r", encoding="utf-8") as f:
                lines = f.readlines()

            if any(link_to_find in line for line in lines):
                continue

            # Count current rows (skip header rows)
            row_number = sum(1 for line in lines if line.startswith("|") and not line.startswith("| #")) - 1

            # Append the formatted row
            with open(md_path, "a", encoding="utf-8") as f:
                f.write(new_row.replace("{row_number}", str(row_number + 1)) + "\n")

    if companies_found:
        print("✅ Link found, deleted, and added to markdown for:")
        print(",".join(companies_found))
    else:
        print("❌ Link not found in any company folder.")

# === RUN FOR ALL ENTRIES FROM ALL MD FILES ===
leetcode_csv_dir = r"D:\code\companies\leetcode-company-wise-problems"
markdown_dir = r"D:\code\javarena-dsa-design\src\main\java\org\example\companies"

# Step 1: Extract all existing solved links & solutions from markdowns
all_solved_dict = {}
for filename in os.listdir(markdown_dir):
    if filename.endswith(".md"):
        md_path = os.path.join(markdown_dir, filename)
        all_solved_dict.update(parse_md_for_links_and_solutions(md_path))

print(f"Found {len(all_solved_dict)} unique solved problems.")

# Step 2: Process each link for all companies
for leetcode_link, solution_link in all_solved_dict.items():
    print(leetcode_link, solution_link)
    find_delete_and_append(leetcode_csv_dir, markdown_dir, leetcode_link, solution_link)
