(ns tic-tac-toe.core.basic-game
  (:require [tic-tac-toe.core.board :as board]
            [tic-tac-toe.core.player :as player]
            [tic-tac-toe.core.decision :as decision]))


(defn take-turn [{:keys [board current-player opponent-player] :as game}]
  (->> (player/get-move game) 
       (board/put-marker board (:marker current-player))
       (assoc {:current-player opponent-player :opponent-player current-player} :board)))

