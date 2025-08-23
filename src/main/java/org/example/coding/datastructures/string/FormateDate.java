package org.example.coding.datastructures.string;

import java.util.HashMap;

public class FormateDate {
    /**
     * Reformat a date string from the format like "20th Oct 2052"
     * into the ISO format "YYYY-MM-DD".
     * <p>
     * Intuition:
     * - Extract the year directly (last 4 characters).
     * - Identify the month abbreviation ("Jan", "Feb", etc.) and map it to its numeric form.
     * - Extract the day while handling suffixes like "th", "st", "nd", "rd".
     * - Pad both month and day with leading zeros if necessary.
     * <p>
     * Approach:
     * 1. Get year from last 4 chars.
     * 2. Extract month substring and compare against known month list.
     * 3. Extract day (first 1–2 characters before suffix).
     * 4. Assemble final string in "YYYY-MM-DD".
     * <p>
     * Time Complexity: O(1) → The string has fixed structure and constant month lookups.
     * Space Complexity: O(1) → Uses a few helper variables and a small array of months.
     */
    public String reformatDate(String date) {
        int n = date.length();

        // Month abbreviations mapping by index
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        StringBuilder sb = new StringBuilder();

        // Step 1: Append Year
        sb.append(date.substring(n - 4)).append("-");

        // Step 2: Extract Month
        // Month always lies between index 4–7 or 5–8 depending on day length
        String month = (n == 12) ? date.substring(4, 7) : date.substring(5, 8);

        // Find numeric month and pad if needed
        for (int i = 0; i < 12; i++) {
            if (months[i].equals(month)) {
                if (i + 1 < 10) sb.append("0");
                sb.append(i + 1).append("-");
                break;
            }
        }

        // Step 3: Extract Day (could be 1 or 2 digits before suffix)
        int digits = 0;
        for (int i = 0; i < 2; i++) {
            if (Character.isDigit(date.charAt(i))) {
                digits++;
            }
        }

        if (digits == 1) {
            sb.append("0").append(date.charAt(0));
        } else {
            sb.append(date.substring(0, 2));
        }

        return sb.toString();
    }

    /**
     * Reformat a date string from the format like "20th Oct 2052"
     * into the ISO format "YYYY-MM-DD".
     * <p>
     * Intuition:
     * - Split the string by spaces: ["20th", "Oct", "2052"].
     * - Use a HashMap to quickly convert month abbreviations into numeric strings.
     * - Remove suffixes ("th", "st", "nd", "rd") from the day.
     * - Pad the day with a leading zero if it’s a single digit.
     * <p>
     * Approach:
     * 1. Split the input into [day, month, year].
     * 2. Use a prebuilt HashMap for month conversion.
     * 3. Clean day string and pad with zero if needed.
     * 4. Assemble result as "YYYY-MM-DD".
     * <p>
     * Time Complexity: O(1) → HashMap lookup and string operations on fixed-size input.
     * Space Complexity: O(1) → Only extra space is the fixed 12-entry month map.
     */
    public String reformatDate2(String date) {
        // Step 1: Build month map
        HashMap<String, String> map = new HashMap<>();
        map.put("Jan", "01");
        map.put("Feb", "02");
        map.put("Mar", "03");
        map.put("Apr", "04");
        map.put("May", "05");
        map.put("Jun", "06");
        map.put("Jul", "07");
        map.put("Aug", "08");
        map.put("Sep", "09");
        map.put("Oct", "10");
        map.put("Nov", "11");
        map.put("Dec", "12");

        // Step 2: Split date into [day, month, year]
        String[] arr = date.split(" ");

        // Step 3: Clean day (remove suffixes)
        String day = arr[0].replace("th", "")
                .replace("st", "")
                .replace("rd", "")
                .replace("nd", "");

        // Step 4: Assemble YYYY-MM-DD
        return arr[2] + "-" + map.get(arr[1]) + "-" + (day.length() > 1 ? "" : "0") + day;
    }


}
