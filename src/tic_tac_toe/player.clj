(ns tic-tac-toe.player)

(defmulti get-move (fn [board player] (:type player)))
