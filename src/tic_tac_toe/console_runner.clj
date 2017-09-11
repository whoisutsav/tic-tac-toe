(ns tic-tac-toe.console-runner
  (:require [tic-tac-toe.console-ui :as console-ui]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.player.human]
            [tic-tac-toe.player.computer]
            [tic-tac-toe.player.hard-computer]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.decision :as decision]))


(defn- take-turn [{:keys [board current-player opponent-player] :as game}]
  (console-ui/print-board board)
  (console-ui/print-turn-prompt (:marker current-player))
  (->> (player/get-move game)
       (board/put-marker board (:marker current-player))
       (assoc {:current-player opponent-player :opponent-player current-player} :board)))

(defn- end-game [board]
  (console-ui/print-board board)
  (if-let [winner (decision/winner board)]
    (console-ui/declare-winner winner)
    (console-ui/declare-draw)))

(defn run [{:keys [board current-player opponent-player] :as game}]
  (if (decision/over? board)
      (end-game board)
      (->> (take-turn game)
           (recur))))


