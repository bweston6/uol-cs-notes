import Control.Monad

small_test = ["##  ##  ##", "  ##  ##  ", "##  ##  ##"]

blank_screen = make_screen 40 6

make_screen :: Int -> Int -> [String]
make_screen x y = [replicate x ' ' | _ <- [1..y]]


print_screen :: [String] -> IO ()
print_screen [] = return ()
print_screen (x:xs) = 
    do
        putStrLn x 
        print_screen xs
        

modify_list :: [a] -> Int -> a -> [a]
modify_list list pos new =
    let
        before = take  pos    list
        after  = drop (pos+1) list
    in
        before ++ [new] ++ after



set :: [String] -> Int -> Int -> Char -> [String]
set screen x y char =
    let
        line       = screen !! y
        new_line   = modify_list line   x char
        new_screen = modify_list screen y new_line
    in
        new_screen


set_list :: [String] -> [(Int, Int, Char)] -> [String]
set_list screen [] = screen
set_list screen ((x,y,c) : xs) =
    set (set_list screen xs) x y c
        

letter_a :: [(Int, Int, Char)]
letter_a = map (\ (x, y) -> (x, y, '#')) [
        (2, 0), (1, 1), (3, 1), (0, 2), (4, 2), (0, 3), (1, 3), (2, 3), 
        (3, 3), (4, 3), (0, 4), (4, 4), (0, 5), (4, 5)
    ]

letter_b :: [(Int, Int, Char)]
letter_b = map (\ (x, y) -> (x, y, '#')) [
        (0, 0), (1, 0), (2, 0), (0, 1), (3, 1), (0, 2), (1, 2), (2, 2), 
        (0, 3), (3, 3), (0, 4), (3, 4), (0, 5), (1, 5), (2, 5)
    ]

shift_letter :: [(Int, Int, Char)] -> Int -> [(Int, Int, Char)]
shift_letter letter shift = map (\ (x, y, c) -> (x + shift, y, c)) letter


big_letters :: [String] -> Int -> IO ()
big_letters screen cursor =
    do
        c <- getLine
        let lett = case head c of 
                'a'       -> letter_a
                'b'       -> letter_b
                otherwise -> []
            new_screen = set_list screen (shift_letter lett cursor)
        print_screen new_screen
        big_letters new_screen (cursor + 6)

main = big_letters blank_screen 0
