(ns tic-tac-toe.basic-game
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.decision :as decision]))


(defn take-turn [{:keys [board current-player opponent-player] :as game}]
  (->> (player/get-move game) 
       (board/put-marker board (:marker current-player))
       (assoc {:current-player opponent-player :opponent-player current-player} :board)))

