# 📝 Standard Code Template - Javarena DSA & Design

This document defines the standard format for all problem solutions in this repository.

---

## 📋 File Structure Template

### Java Class Template

```java
package com.javarena.dsa.[category].[subcategory];

import java.util.*;  // Only import what's needed

/**
 * [Problem Number]. [Problem Name]
 *
 * <p><b>Problem Link:</b> 
 * <a href="[LeetCode/GFG URL]">[Platform] - [Problem Name]</a>
 *
 * <p><b>Difficulty:</b> [Easy | Medium | Hard]
 *
 * <p><b>Topics:</b> [Topic1], [Topic2], [Topic3]
 *
 * ---
 *
 * <p><b>Problem Statement:</b><br>
 * [Concise problem description in 2-3 lines or bullet points]
 * - Constraint 1
 * - Constraint 2
 *
 * <p><b>Example:</b>
 * <pre>
 * Input: [sample input]
 * Output: [sample output]
 * Explanation: [brief explanation]
 * </pre>
 *
 * ---
 *
 * <p><b>Intuition:</b><br>
 * [Core insight that leads to the solution - the "aha!" moment]
 * - Why this approach works
 * - Key observation that simplifies the problem
 * - Connection to known patterns or techniques
 *
 * ---
 *
 * <p><b>Approach:</b>
 * <ol>
 *   <li>Step 1: [Clear action]</li>
 *   <li>Step 2: [Clear action]</li>
 *   <li>Step 3: [Clear action]</li>
 *   <li>Step 4: [Clear action]</li>
 * </ol>
 *
 * ---
 *
 * <p><b>Algorithm:</b>
 * <pre>
 * 1. [Pseudocode line 1]
 * 2. [Pseudocode line 2]
 * 3. [Pseudocode line 3]
 *    a. [Sub-step if needed]
 *    b. [Sub-step if needed]
 * 4. [Pseudocode line 4]
 * </pre>
 *
 * ---
 *
 * <p><b>Time Complexity:</b> O(...)<br>
 * [Brief explanation of why this complexity]
 *
 * <p><b>Space Complexity:</b> O(...)<br>
 * [Brief explanation of space usage]
 *
 * ---
 *
 * <p><b>Edge Cases:</b>
 * <ul>
 *   <li>Empty input: [how handled]</li>
 *   <li>Single element: [how handled]</li>
 *   <li>All same elements: [how handled]</li>
 *   <li>Maximum constraints: [how handled]</li>
 * </ul>
 *
 * @author Javarena Team
 * @see <a href="[related problem link if any]">Related Problem</a>
 */
public class ProblemName {
    
    /**
     * [Main solution method - one-line description]
     *
     * @param param1 [parameter description]
     * @param param2 [parameter description]
     * @return [return value description]
     */
    public ReturnType methodName(ParamType param1, ParamType param2) {
        // Step 1: [Comment explaining this section]
        
        // Step 2: [Comment explaining this section]
        
        // Step 3: [Comment explaining this section]
        
        return result;
    }
    
    /**
     * Helper method - [description of what it does]
     *
     * @param param [parameter description]
     * @return [return value description]
     */
    private ReturnType helperMethod(ParamType param) {
        // Implementation
        return result;
    }
    
    // ==================== ALTERNATIVE APPROACHES ====================
    
    /**
     * [Alternative approach name] - [one-line description]
     * 
     * <p><b>Intuition:</b> [Why this approach is different]
     * 
     * <p><b>Time Complexity:</b> O(...)
     * <br><b>Space Complexity:</b> O(...)
     * 
     * <p><b>Trade-offs:</b> [When to use this vs main approach]
     *
     * @param param [parameter description]
     * @return [return value description]
     */
    public ReturnType alternativeApproach(ParamType param) {
        // Implementation
        return result;
    }
}
```

---

## 📚 README Template for Each Algorithm Directory

```markdown
# 📘 [Algorithm/Technique Name]

## 📖 Introduction

[2-3 sentence introduction to the algorithm/technique]

Key characteristics:
- [Characteristic 1]
- [Characteristic 2]
- [Characteristic 3]

---

## 💡 Intuition

### Why This Technique Works

[Explain the fundamental principle behind this technique]

### When to Use

✅ **Use when:**
- [Scenario 1]
- [Scenario 2]
- [Scenario 3]

❌ **Avoid when:**
- [Scenario 1]
- [Scenario 2]

---

## 🎯 Common Patterns

### Pattern 1: [Pattern Name]
**Description:** [Brief description]  
**Example Problems:** [Problem 1], [Problem 2]

### Pattern 2: [Pattern Name]
**Description:** [Brief description]  
**Example Problems:** [Problem 1], [Problem 2]

---

## ⏱️ Complexity Analysis

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| [Operation 1] | O(...) | O(...) |
| [Operation 2] | O(...) | O(...) |

---

## 📝 Algorithm Template

```
function algorithmName(input):
    // Step 1: [Description]
    [pseudocode]
    
    // Step 2: [Description]
    [pseudocode]
    
    // Step 3: [Description]
    [pseudocode]
    
    return result
```

---

## 🧪 Practice Problems

### Easy Level

| # | Problem | Solution | Difficulty | Topics | Companies |
|---|---------|----------|------------|--------|-----------|
| 1 | [Problem Name](link) | [Solution.java](./Solution.java) | Easy | Tag1, Tag2 | Company1 |

### Medium Level

| # | Problem | Solution | Difficulty | Topics | Companies |
|---|---------|----------|------------|--------|-----------|
| 1 | [Problem Name](link) | [Solution.java](./Solution.java) | Medium | Tag1, Tag2 | Company1, Company2 |

### Hard Level

| # | Problem | Solution | Difficulty | Topics | Companies |
|---|---------|----------|------------|--------|-----------|
| 1 | [Problem Name](link) | [Solution.java](./Solution.java) | Hard | Tag1, Tag2, Tag3 | Company1, Company2 |

---

## 💼 Real-World Applications

1. **[Application 1]**  
   [Brief description of how this technique is used in real systems]

2. **[Application 2]**  
   [Brief description of how this technique is used in real systems]

---

## 📊 Comparison with Similar Techniques

| Aspect | [This Technique] | [Alternative 1] | [Alternative 2] |
|--------|-----------------|----------------|-----------------|
| Time Complexity | O(...) | O(...) | O(...) |
| Space Complexity | O(...) | O(...) | O(...) |
| Best Use Case | [scenario] | [scenario] | [scenario] |
| Limitations | [limitation] | [limitation] | [limitation] |

---

## 🎓 Learning Path

### Prerequisites
- [Concept 1]
- [Concept 2]
- [Concept 3]

### Recommended Problem Sequence
1. Start with: [Easy problem that teaches basics]
2. Then try: [Medium problem that builds on basics]
3. Master with: [Hard problem that combines concepts]

---

## 📚 Additional Resources

- **Video Tutorials:**
  - [Resource 1 with link]
  - [Resource 2 with link]

- **Articles:**
  - [Article 1 with link]
  - [Article 2 with link]

- **Related Topics:**
  - [Topic 1 with link to folder]
  - [Topic 2 with link to folder]

---

## ✅ Checklist for Mastery

- [ ] Understand the core intuition
- [ ] Can explain the approach without looking at code
- [ ] Solved all Easy problems
- [ ] Solved at least 50% of Medium problems
- [ ] Attempted all Hard problems
- [ ] Can identify when to use this technique in new problems
- [ ] Can explain time/space complexity analysis
- [ ] Understand trade-offs with alternative approaches

---

**Last Updated:** [Date]  
**Total Problems:** [Count]  
**Contributors:** [Names]
```

---

## 🎯 Code Quality Standards

### Required Elements

✅ **Every problem solution MUST have:**
1. Complete class-level JavaDoc with all sections
2. Problem link (LeetCode, GFG, or other platform)
3. Difficulty level clearly marked
4. At least 2 relevant topic tags
5. Time and Space complexity with explanations
6. Intuition section explaining the "why"
7. Step-by-step approach
8. All public methods documented with @param and @return
9. Inline comments for complex logic
10. Edge cases documented

### Forbidden Practices

❌ **Never do:**
1. Leave methods without documentation
2. Use magic numbers without constants or explanation
3. Write code without intuition/explanation
4. Skip complexity analysis
5. Ignore edge cases
6. Use inconsistent naming conventions
7. Leave TODO comments in committed code
8. Include main() method unless it's a utility class

---

## 🔍 Code Review Checklist

Before committing any problem solution, verify:

- [ ] Class-level JavaDoc complete with all required sections
- [ ] Problem link is valid and accessible
- [ ] Difficulty level matches the source platform
- [ ] Topics/tags are accurate and relevant
- [ ] Time complexity is correct and explained
- [ ] Space complexity is correct and explained
- [ ] Intuition clearly explains the core insight
- [ ] Approach is broken down into clear steps
- [ ] All public methods have proper JavaDoc
- [ ] Helper methods are documented
- [ ] Code follows naming conventions
- [ ] No unused imports or variables
- [ ] Edge cases are handled and documented
- [ ] Alternative approaches included if significant
- [ ] README updated with problem entry
- [ ] Package declaration matches directory structure
- [ ] Code compiles without errors
- [ ] No IDE warnings (unused variables, etc.)

---

## 📊 Documentation Metrics

### Minimum Requirements

| Element | Minimum |
|---------|---------|
| Class JavaDoc | 15 lines |
| Intuition | 3 bullet points |
| Approach | 4 steps |
| Edge Cases | 3 cases |
| Method Documentation | All public methods |
| Inline Comments | One per logical block |

---

This template ensures consistency, quality, and educational value across all problem solutions in the repository.

