(ns tic-tac-toe.player)

; TODO combine arguments into map
(defmulti get-move (fn [game] (:type (:current-player game))))
