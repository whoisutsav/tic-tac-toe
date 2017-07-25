(ns tic-tac-toe.board) 

(def empty-space :_)

(defn new-board []
 (vec (repeat 9 empty-space)))

(defn size [board]
  (count board))

(defn apply-move [cell board marker]
  (assoc board (dec cell) marker))

(defn bget [cell board]
  (let [marker (nth board (dec cell))]
    (if (= empty-space marker)
      nil
      marker)))
