(ns tic-tac-toe.turn
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.console-output :as console-output]
            [tic-tac-toe.player :as player]))

(defn- switch-player [current-player]
  (case current-player
    :x :o
    :o :x))

(defn take-turn [turn]
  (let [{:keys [board markers current-player]} turn]
    (-> turn
        (assoc :board (-> board 
                          (player/get-move (current-player markers))
                          (board/apply-move board current-player)))
        (assoc :current-player (switch-player current-player)))
    ))


