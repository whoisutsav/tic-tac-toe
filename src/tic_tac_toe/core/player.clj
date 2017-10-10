(ns tic-tac-toe.core.player)

(defmulti get-move (fn [game] (:type (:current-player game))))
