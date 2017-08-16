(ns tic-tac-toe.console-runner
  (:require [tic-tac-toe.console-ui :as console-ui]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.player.human]
            [tic-tac-toe.player.computer]
            [tic-tac-toe.player.hard-computer]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.decision :as decision]))

(defn- take-turn [board player]
  (console-ui/print-board board)
  (let [marker (:marker player)] 
    (console-ui/print-turn marker)
    (let [move (player/get-move board player)]
      (board/put-marker board move marker))))

(defn- complete-game [board]
  (console-ui/print-board board)
  (if-let [winner (decision/winner board)]
    (console-ui/declare-winner winner)
    (console-ui/declare-draw)))

(defn run [{:keys [board current-player opponent-player]}]
  (if (decision/over? board)
      (complete-game board)
      (let [updated-board (take-turn board current-player)]
        (recur {
              :board updated-board 
              :current-player opponent-player
              :opponent-player current-player}))))


