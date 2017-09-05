(ns tic-tac-toe.player.spec-helper
  (:require [tic-tac-toe.player :refer [get-move]]
            [tic-tac-toe.player.hard-computer :refer :all] 
            [tic-tac-toe.decision :as decision]
            [tic-tac-toe.board :as board]))

(defn can-i-win? [my-player computer-player is-my-turn board]
  (let [my-marker (:marker my-player)
        computer-marker (:marker computer-player)]
    (if (decision/over? board)
      (= my-marker (decision/winner board))
      (if is-my-turn
        (->> (possible-boards board my-marker) 
             (map #(can-i-win? my-player computer-player false %))
             (reduce #(or %1 %2)))
        (->> (get-move board computer-player my-player)
             (board/put-marker board computer-marker)
             (recur my-player computer-player true)))))) 


(defn can-i-win-all-boards? [board-size my-player computer-player]
  (can-i-win? my-player computer-player true (board/new-board board-size)))
