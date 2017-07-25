(ns tic-tac-toe.board) 

(def empty-space :_)

(defn size [board]
  (count board))

(defn new-board []
 (vec (repeat 9 empty-space)))

(defn apply-move [board move marker]
  (assoc board (dec move) marker))

; TODO - rename to get-cell?
(defn bget [cell board]
  (let [marker (nth board (dec cell))]
    (if (= empty-space marker)
      nil
      marker)))
