(ns tic-tac-toe.cli-runner
  (:require [tic-tac-toe.console :as console]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.decision :as decision]))

(defn- swap-marker [current-marker players]
  (let [markers (keys players)]
    (if (= (first markers) current-marker)
      (second markers)
      (first markers))))

(defn- take-turn [board players marker]
  (console/print-board board)
  (console/print-turn-message marker)
  (let [move (player/get-move board (marker players))]
    (board/apply-move board move marker)))

(defn- complete-game [board]
  (console/print-board board)
  (if-let [winner (decision/winner board)]
    (console/declare-winner winner)
    (console/declare-draw)))

(defn run [{:keys [board current-marker players]}]
  (if (decision/over? board)
      (complete-game board)
      (recur {
              :board (take-turn board players current-marker)
              :current-marker (swap-marker current-marker players)
              :players players})))


