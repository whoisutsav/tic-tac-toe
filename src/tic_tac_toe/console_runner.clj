(ns tic-tac-toe.console-runner
  (:require [tic-tac-toe.console-ui :as console-ui]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.player.human]
            [tic-tac-toe.player.computer]
            [tic-tac-toe.player.hard-computer]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.decision :as decision]))


(defn- take-turn [board current-player opponent-player]
  (console-ui/print-board board)
  (console-ui/print-turn-prompt (:marker current-player))
  (->> (player/get-move board current-player opponent-player)
       (board/put-marker board (:marker current-player))
       (assoc {:opponent-player current-player :current-player opponent-player} :board)))

(defn- complete-game [board]
  (console-ui/print-board board)
  (if-let [winner (decision/winner board)]
    (console-ui/declare-winner winner)
    (console-ui/declare-draw)))

(defn run [{:keys [board current-player opponent-player]}]
  (if (decision/over? board)
      (complete-game board)
      (->> (take-turn board current-player opponent-player)
           (recur))))


