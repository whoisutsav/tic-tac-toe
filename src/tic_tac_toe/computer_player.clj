(ns tic-tac-toe.computer-player
  (:require [tic-tac-toe.player :refer :all]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.console :as console]))

(defn- find-empty-space [board]
  (let [move (rand-nth (range 1 (inc (board/size board))))]
   (if (nil? (board/get-marker move board))
     move
     (recur board))))

(defmethod get-move :computer [board _]
  (let [move (find-empty-space board)]
    (console/print-computer-move move)
    move))
