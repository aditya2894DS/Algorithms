# Recursive Algorithms

> Notes based on [Recursive Algorithm in Programming: Concept, Examples & Use Cases](https://uncodemy.com/blog/recursive-algorithm-in-programming-concept-examples-use-cases) (Uncodemy), with code examples added.

## What is recursion?

A recursive algorithm solves a problem by calling itself on a smaller instance of the same problem, until it reaches a case simple enough to answer directly.

Every recursive function needs two parts:

- **Base case**: the stopping condition, answered without recursing.
- **Recursive case**: the step that reduces the problem and calls the function again.

```python
def countdown(n):
    if n == 0:          # base case
        print("Done")
        return
    print(n)
    countdown(n - 1)    # recursive case
```

## Characteristics

| Trait | Meaning |
|---|---|
| Problem decomposition | The problem splits into smaller sub-problems of the same shape |
| Base condition | Guarantees the calls eventually stop |
| Call-stack usage | Each call adds a stack frame, so deep recursion costs memory |

## When to use recursion

- Hierarchical or tree-shaped data (trees, file systems, nested JSON)
- Problems that naturally shrink into smaller copies of themselves
- Backtracking (N-Queens, Sudoku, permutations, maze solving)
- Divide and conquer (merge sort, quick sort, binary search)

## Classic examples

### Factorial

`n! = n × (n-1)!`, with `0! = 1`.

```python
def factorial(n):
    if n <= 1:
        return 1
    return n * factorial(n - 1)
```

Time: O(n). Space: O(n) stack.

### Fibonacci

`F(n) = F(n-1) + F(n-2)`, with `F(0) = 0`, `F(1) = 1`.

```python
def fib(n):
    if n < 2:
        return n
    return fib(n - 1) + fib(n - 2)
```

Time: O(2ⁿ). The same sub-problems are recomputed many times (see [Memoization](#memoization)).

### Tower of Hanoi

Move `n` disks from `src` to `dst` using `aux`, never placing a larger disk on a smaller one.

```python
def hanoi(n, src, dst, aux):
    if n == 0:
        return
    hanoi(n - 1, src, aux, dst)
    print(f"Move disk {n} from {src} to {dst}")
    hanoi(n - 1, aux, dst, src)
```

Moves: 2ⁿ − 1. Time: O(2ⁿ).

### Binary search

Search a sorted array by halving the range each call.

```python
def binary_search(arr, target, lo, hi):
    if lo > hi:
        return -1
    mid = (lo + hi) // 2
    if arr[mid] == target:
        return mid
    if arr[mid] < target:
        return binary_search(arr, target, mid + 1, hi)
    return binary_search(arr, target, lo, mid - 1)
```

Time: O(log n). Space: O(log n) stack.

### Tree traversals

```python
def preorder(node):   # root, left, right
    if node:
        print(node.val)
        preorder(node.left)
        preorder(node.right)

def inorder(node):    # left, root, right
    if node:
        inorder(node.left)
        print(node.val)
        inorder(node.right)

def postorder(node):  # left, right, root
    if node:
        postorder(node.left)
        postorder(node.right)
        print(node.val)
```

Time: O(n). Space: O(h), where h is the tree height.

## Advantages and disadvantages

| Advantages | Disadvantages |
|---|---|
| Short, readable code | Extra memory for each stack frame |
| Maps naturally to recursive structures (trees, graphs) | Risk of stack overflow on deep inputs |
| Makes divide-and-conquer and backtracking easy to express | Can be very slow when sub-problems overlap (naive Fibonacci) |
| | Harder to trace and debug |

## Recursion vs. iteration

| | Recursion | Iteration |
|---|---|---|
| Code style | Concise, elegant | Often longer, more explicit |
| Memory | O(depth) call stack | Usually O(1) extra |
| Speed | Function-call overhead | Generally faster |
| Debugging | Harder to trace | Easier to step through |
| Best for | Trees, backtracking, divide and conquer | Simple loops, linear processing |

## Optimizing recursion

### Memoization

Cache results so each sub-problem is solved once. Fibonacci drops from O(2ⁿ) to O(n).

```python
from functools import lru_cache

@lru_cache(maxsize=None)
def fib(n):
    if n < 2:
        return n
    return fib(n - 1) + fib(n - 2)
```

### Tail recursion

A call is *tail recursive* when the recursive call is the last operation, so no work waits on the stack. Some languages (Scheme, Scala with `@tailrec`, many C/C++ compilers at higher optimization levels) can reuse the stack frame. **Python and Java do not** perform tail-call optimization.

```python
def factorial_tail(n, acc=1):
    if n <= 1:
        return acc
    return factorial_tail(n - 1, acc * n)
```

### Convert to iteration

When depth can be large, rewrite with a loop or an explicit stack:

```python
def factorial_iter(n):
    result = 1
    for i in range(2, n + 1):
        result *= i
    return result
```

## Common mistakes

- **Missing or wrong base case**: infinite recursion leading to a stack overflow (`RecursionError` in Python, default limit about 1000).
- **Not shrinking the input**: the recursive call must move toward the base case.
- **Ignoring overlapping sub-problems**: exponential time where memoization would give linear.
- **Recursing too deep**: use iteration or raise the limit carefully (`sys.setrecursionlimit`).

## Real-world applications

- **Operating systems**: walking directory trees
- **Compilers**: parsing expressions and syntax trees
- **AI and games**: minimax, game-tree search, backtracking solvers
- **Graphics**: fractals, flood fill, spatial trees
- **Mathematics**: combinatorics, divide-and-conquer algorithms, recurrences
