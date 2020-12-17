module Main (get_maze, print_maze, is_wall, place_player, move, can_move, game_loop, get_path, main) where 

import System.Environment
import Control.Monad

maze_path = "/home/ben/Documents/University/COMP105/Assessments/Assessment 3/Resources/maze6.txt"

-- Useful code from Lecture 25
-- You may use this freely in your solutions

get :: [String] -> Int -> Int -> Char
get maze x y = (maze !! y) !! x 

modify_list :: [a] -> Int -> a -> [a]
modify_list list pos new =
    let
        before = take  pos    list
        after  = drop (pos+1) list
    in
        before ++ [new] ++ after

set :: [String] -> Int -> Int -> Char -> [String]
set maze x y char = 
    let
        line = maze !! y
        new_line = modify_list line x char
        new_maze = modify_list maze y new_line
    in
        new_maze

---- Part A

-- Question 1

get_maze :: String -> IO [String]
get_maze file = do
        maze <- readFile file
        return . lines $ maze

-- Question 2

print_maze :: [String] -> IO ()
print_maze maze = putStrLn . unlines $ maze

-- Question 3

is_wall :: [String] -> (Int, Int) -> Bool
is_wall maze (x,y) = '#' == get maze x y 

-- Question 4

place_player :: [String] -> (Int, Int) -> [String]
place_player maze (x,y) = set maze x y '@'

---- Part B

-- Question 5

move :: (Int, Int) -> Char -> (Int, Int)
move (x,y) key
        | key == 'w' = (x,y-1)
        | key == 's' = (x,y+1)
        | key == 'd' = (x+1,y)
        | key == 'a' = (x-1,y)
        | otherwise  = (x,y)

-- Question 6

can_move :: [String] -> (Int, Int) -> Char -> Bool
can_move maze (x,y) key = not . is_wall maze $ move (x,y) key

-- Question 7

game_loop :: [String] -> (Int, Int) -> IO ()
game_loop maze (x,y) = do
        let state = place_player maze (x,y)
        print_maze state
        keyLine <- getLine
        let key = head keyLine
        when (can_move maze (x,y) key) (game_loop maze (move (x,y) key))
        game_loop maze (x,y)

---- Part C

-- Question 8 

get_path_helper :: [String] -> (Int,Int) -> (Int,Int) -> (Int,Int) -> [(Int,Int)] -> [(Int,Int)]
get_path_helper _ current end start path
        | current == end = path ++ [current]
get_path_helper maze (x,y) (ex,ey) start path
        | dead_end                 = get_path_helper wall_off start (ex,ey) start []
        | (cm 'd') && nin_path 'd' = get_path_helper maze (move (x,y) 'd') (ex,ey) start (path ++ [(x,y)])
        | (cm 's') && nin_path 's' = get_path_helper maze (move (x,y) 's') (ex,ey) start (path ++ [(x,y)])
        | (cm 'a') && nin_path 'a' = get_path_helper maze (move (x,y) 'a') (ex,ey) start (path ++ [(x,y)])
        | (cm 'w') && nin_path 'w' = get_path_helper maze (move (x,y) 'w') (ex,ey) start (path ++ [(x,y)])
        where dead_end = ((cm 'd' && cnm 's' && cnm 'a' && cnm 'w') || (cnm 'd' && cm 's' && cnm 'a' && cnm 'w') || (cnm 'd' && cnm 's' && cm 'a' && cnm 'w') || (cnm 'd' && cnm 's' && cnm 'a' && cm 'w')) && ((x,y) /= start)
              cm key       = can_move maze (x,y) key
              cnm key      = not $ cm key
              wall_off     = set maze x y '#'
              nin_path key = not $ elem (move (x,y) key) path

get_path :: [String] -> (Int, Int) -> (Int, Int) -> [(Int, Int)]
get_path maze start end = get_path_helper maze start end start []

-- Question 9

maze_end :: [String] -> (Int,Int)
maze_end maze = (( length . head $ maze) - 2, (length  maze) - 2)

dotted_maze :: [String] -> [(Int, Int)] -> [String]
dotted_maze maze []         = maze 
dotted_maze maze ((x,y):xs) = dotted_maze (set maze x y '.') xs

main :: IO ()
main = do
        path <- getArgs
        maze <- get_maze . head $ path
        print_maze . dotted_maze maze . get_path maze (1,1) $ maze_end maze
