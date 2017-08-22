(ns tic-tac-toe.player)

(defmulti get-move (fn [board current-player opponent-player] (:type current-player)))
