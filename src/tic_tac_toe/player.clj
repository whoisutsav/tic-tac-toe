(ns tic-tac-toe.player
  (:require [tic-tac-toe.console :as console]
            [tic-tac-toe.validation :as validation]
            [tic-tac-toe.board :as board]))


(defn get-marker [player]
  (console/show-marker-prompt (if (= :primary player) 1 2))
  (let [marker (console/get-user-input)]
    (if-let [error-str (:error (validation/marker marker))]
      (do (console/show-error error-str)
          (recur player))
      marker)))

(defmulti get-move (fn [board player] (:type player)))

(defmethod get-move :human [board player]
  (let [move (console/prompt-for-move (:marker player) board)] 
    (if-let [error-str (:error (validation/move board move))] 
      (do (console/show-error error-str) 
          (recur board player))
      (read-string move))))

(defn- find-empty-space [board]
  (let [move (rand-nth (range 0 (board/size board)))]
   (if (board/empty-space? move board)
     move
     (recur board))))

(defmethod get-move :computer [board player]
  (let [move (find-empty-space board)]
    (console/print-computer-move move)
    move))
