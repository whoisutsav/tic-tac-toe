(ns tic-tac-toe.player)

; TODO combine arguments into map
(defmulti get-move (fn [board current-player opponent-player] (:type current-player)))
