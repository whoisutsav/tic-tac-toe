(ns tic-tac-toe.player
  (:require [tic-tac-toe.console :as console]
            [tic-tac-toe.validation :as validation]
            [tic-tac-toe.board :as board]))


; TODO - change name of player arg...confusing
(defn get-marker [player]
  (console/show-marker-prompt (if (= :primary player) 1 2))
  (let [marker (console/get-user-input)]
    (if-let [error-str (:error (validation/marker marker))]
      (do (console/show-error error-str)
          (recur player))
      marker)))

(defmulti get-move (fn [board marker player] (:type player)))

(defmethod get-move :human [board marker player]
  (let [move (console/prompt-for-move marker board)] 
    (if-let [error-str (:error (validation/move board move))] 
      (do (console/show-error error-str) 
          (recur board marker player))
      (read-string move))))

; TODO - should I put this into board?
(defn- find-empty-space [board]
  (let [move (rand-nth (range 1 (inc (board/size board))))]
   (if (nil? (board/bget move board))
     move
     (recur board))))

(defmethod get-move :computer [board marker player]
  (let [move (find-empty-space board)]
    (console/print-computer-move move)
    move))
