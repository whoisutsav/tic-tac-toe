(ns tic-tac-toe.console-runner
  (:require [tic-tac-toe.console-interface :as console]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.player.console-human :refer :all]
            [tic-tac-toe.player.console-computer :refer :all] ;TODO how to include multimethod across namespaces 
            [tic-tac-toe.board :as board]
            [tic-tac-toe.decision :as decision]))

(defn- take-turn [board player]
  (console/print-board board)
  (let [marker (:marker player)] 
    (console/state-turn marker)
    (let [move (player/get-move board player)]
      (board/put-marker board move marker))))

(defn- complete-game [board]
  (console/print-board board)
  (if-let [winner (decision/winner board)]
    (console/declare-winner winner)
    (console/declare-draw)))

(defn run [{:keys [board current-player opponent-player]}]
  (if (decision/over? board)
      (complete-game board)
      (let [updated-board (take-turn board current-player)]
        (recur {
              :board updated-board 
              :current-player opponent-player
              :opponent-player current-player}))))


