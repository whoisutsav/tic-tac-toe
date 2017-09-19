(ns tic-tac-toe.web.runner
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.decision :as decision]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.player.human-web]
            [tic-tac-toe.player.computer]
            [tic-tac-toe.player.hard-computer]
            [tic-tac-toe.web.response :as response]))

(defn- take-turn [{:keys [board current-player opponent-player] :as game}]
  (->> (player/get-move game) 
       (board/put-marker board (:marker current-player))
       (assoc {:current-player opponent-player :opponent-player current-player} :board)))

(defn- end-game [board]
  (if-let [winner (decision/winner board)]
    (response/win-response board winner)
    (response/draw-response board)
    ))

(defn handle [game]
  (let [updated-game (take-turn game)
        updated-board (:board updated-game)]
    (if (decision/over? updated-board)
      (end-game updated-board)
      (response/game-response updated-game))))
