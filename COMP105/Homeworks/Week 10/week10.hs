-- Lecture 26 - Lazy Evaluation
{-
1. Error - Divide by zero.
2. No error
3. No error
4. Error - error
5. No error
-}

-- Lecture 26 - Lazy Evaluation on Lists
{-
1. This will terminate as take returns 4 elements of the infinite list.
2. This will not terminate as drop will return the infinite list minus the first 4 elements.
3. This will not terminate as map will return the infinite list with each element multiplied by two.
4. This will terminate as the `!! 1000` will return the 1001st element of the list
-}
