(ns tic-tac-toe.player)

(defmulti get-move (fn [game] (:type (:current-player game))))
