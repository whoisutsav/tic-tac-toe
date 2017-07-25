(ns tic-tac-toe.decision
  (:require [tic-tac-toe.board :as board]))

(def win-lines [[1 2 3] [4 5 6] [7 8 9]
                [1 4 7] [2 5 8] [3 6 9]
                [1 5 9] [3 5 7]])

(defn- no-more-moves? [board]
  (not-any? #(nil? (board/bget % board)) 
          (range 1 (inc (board/size board))))) 

(defn winner [board]
  (loop [lines win-lines]
    (when-first [line lines]
      (let [marks (map #(board/bget % board) line)
            first-mark (first marks)] 
        (if (and (not= nil first-mark) (every? #(= first-mark %) marks))
        first-mark 
        (recur (rest lines)))))))

(defn over? [board]
  (or (not= (winner board) nil)
      (no-more-moves? board)))
