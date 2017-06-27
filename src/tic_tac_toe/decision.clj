(ns tic-tac-toe.decision
  (:require [tic-tac-toe.board :as board]))

(def wins [[0 1 2] [3 4 5] [6 7 8]
           [0 3 6] [1 4 7] [2 5 8]
           [0 4 8] [2 4 6]])

(defn no-more-moves? [board]
  (not-any? #(board/empty-space? % board) 
          (range 0 (count board)))) 

(defn winner [board]
  (loop [lines wins]
    (when-let [marks (seq (map #(nth board %) (first lines)))]
      (if (or (= marks (repeat 3 :x)) (= marks (repeat 3 :o)))
        (first marks)
        (recur (rest lines))))))

