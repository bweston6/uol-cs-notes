-- Do not alter the following line
module Assignment2 (transaction_to_string, trade_report_list, stock_test, get_trades, trade_report, update_money, profit, profit_report, complex_profit_report) where


type Transaction = (Char, Int, Int, String, Int) 

test_log :: [Transaction]
test_log = [('B', 100, 1104,  "VTI",  1),
            ('B', 200,   36, "ONEQ",  3),
            ('B',  50, 1223,  "VTI",  5),
            ('S', 150, 1240,  "VTI",  9),
            ('B', 100,  229, "IWRD", 10),
            ('S', 200,   32, "ONEQ", 11), 
            ('S', 100,  210, "IWRD", 12)
            ]


-- Part A


transaction_to_string :: Transaction -> String
transaction_to_string (a, b, c, d, e) = let
                                                        bought = case a of 'B' -> "Bought"
                                                                           'S' -> "Sold"
                                                in
                                                        bought ++ " " ++ show b ++ " units of " ++ d ++ " for " ++ show c ++ " pounds each on day " ++ show e


trade_report_list :: [Transaction] -> [String]
trade_report_list list = map transaction_to_string list


stock_test :: String -> Transaction -> Bool
stock_test string (_,_,_,y,_)
        | string == y    = True
        | otherwise = False


get_trades :: String -> [Transaction] -> [Transaction]
get_trades string list = filter (\ (_,_,_,tradeName,_) -> tradeName == string) list


trade_report :: String -> [Transaction] -> String
trade_report string x = unlines $ trade_report_list $ get_trades string x


-- Part B


update_money :: Transaction -> Int -> Int
update_money (x,y,z,_,_) purse
        | x == 'B'  = purse - y * z
        | otherwise = purse + y * z


profit :: [Transaction] -> String -> Int
profit list string = foldr (\ x acc -> update_money x acc ) 0 (get_trades string list)


profit_report :: [String] -> [Transaction] -> String
profit_report stringList list = unlines (zipWith (\ x y -> y ++ ": " ++ show x ) (map (profit list) stringList) stringList)


-- Part C


test_str_log = "BUY 100 VTI 1\nBUY 200 ONEQ 3\nBUY 50 VTI 5\nSELL 150 VTI 9\nBUY 100 IWRD 10\nSELL 200 ONEQ 11\nSELL 100 IWRD 12\n"



type Prices = [(String, [Int])]

test_prices :: Prices
test_prices = [
                ("VTI", [1689, 1785, 1772, 1765, 1739, 1725, 1615, 1683, 1655, 1725, 1703, 1726, 1725, 1742, 1707, 1688, 1697, 1688, 1675]),
                ("ONEQ", [201, 203, 199, 199, 193, 189, 189, 183, 185, 190, 186, 182, 186, 182, 182, 186, 183, 179, 178]),
                ("IWRD", [207, 211, 213, 221, 221, 222, 221, 218, 226, 234, 229, 229, 228, 222, 218, 223, 222, 218, 214])
              ]

list_stock_name :: Prices -> [String]
list_stock_name prices = map fst prices

price_finder :: String -> Int -> Prices -> Int
price_finder string day prices = case (head (filter (\ (x,_) -> x == string ) prices)) of (_,x) -> x !! (day - 1)

string_to_trade_log :: String -> Prices -> [Transaction]
string_to_trade_log string prices =
        let 
                parsed        = map words . lines $ string
                string_to_int = (\ x -> read x :: Int)
        in
                map (\ [a,b,c,d] -> ((if a == "BUY" then 'B' else 'S'),(string_to_int b),(price_finder c (string_to_int d) prices),c,(string_to_int d))) parsed

complex_profit_report :: String -> Prices -> String
complex_profit_report string prices = profit_report (list_stock_name prices) (string_to_trade_log string prices)
