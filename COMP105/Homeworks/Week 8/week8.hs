-- Lecture 23 - getLine and putStrLn
echo :: IO ()
echo = do
        str <- getLine
        putStrLn str

double_echo :: IO ()
double_echo = do
        str <- getLine
        putStrLn $ str ++ "\n" ++ str

put_two_strs :: IO ()
put_two_strs = do
        str1 <- getLine
        str2 <- getLine
        putStrLn $ str1 ++ "\n" ++ str2

-- Lecture 23 - Let in do Blocks

times_two :: IO ()
times_two = do
        x <- getLine
        let     num = read x :: Int
                out = num * 2
        putStrLn $ show out

add :: IO ()
add = do
        x <- getLine
        y <- getLine
        let     sum = (read x :: Int) + (read y :: Int)
        putStrLn $ show sum

guess_42 :: IO ()
guess_42 = do
        x <- getLine
        if x == "42"
                then putStrLn "correct"
                else putStrLn "wrong"

-- Lecture 23 - Return

get_bool :: IO Bool
get_bool = do
        x <- getLine
        let     bool = read x :: Bool
        return bool

get_two_and_add :: IO Int
get_two_and_add = do
        x <- getLine
        y <- getLine
        let     sum = (read x :: Int) + (read y :: Int)
        return sum

get_two_strings :: IO (String,String)
get_two_strings = do
        x <- getLine
        y <- getLine
        let     tuple = (x,y)
        return tuple
        
