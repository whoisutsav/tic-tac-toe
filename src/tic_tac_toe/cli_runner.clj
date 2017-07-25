(ns tic-tac-toe.cli-runner
  (:require [tic-tac-toe.console :as console]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.decision :as decision]))

(defn- switch-player [marker players]
  (some #(if (not= marker %) %) (keys players)))

(defn- take-turn [board players marker]
  (console/print-board board)
  (let [move (player/get-move board marker (marker players))]
    (board/apply-move move board marker)))

(defn- complete-game [board]
  (if-let [winner (decision/winner board)]
    (console/declare-winner winner)
    (console/declare-draw)))

(defn run [{:keys [board current-marker players]}]
  (if (decision/over? board)
      (complete-game board)
      (recur {
              :board (take-turn board players current-marker)
              :current-marker (switch-player current-marker players)
              :players players})))


