(ns tic-tac-toe.board) 

(def empty-space :_)
(def x-player :x)
(def o-player :o)

(defn new-board []
 (vec (repeat 9 empty-space)))

(defn apply-move [cell board player]
  (assoc board cell player))

(defn empty-space? [cell board]
  (= empty-space (nth board cell)))

(defn size [board]
  (count board))
