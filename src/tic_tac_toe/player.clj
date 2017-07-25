(ns tic-tac-toe.player
  (:require [tic-tac-toe.console :as console]
            [tic-tac-toe.validation :as validation]
            [tic-tac-toe.board :as board]))


(defn get-marker [order]
  (console/show-marker-prompt (if (= :primary order) 1 2))
  (let [marker (console/get-user-input)]
    (if-let [error-str (:error (validation/marker marker))]
      (do (console/show-error error-str)
          (recur order))
      (keyword marker))))

(defmulti get-move (fn [board player] (:type player)))

(defmethod get-move :human [board _]
  (let [move (console/prompt-for-move)] 
    (if-let [error-str (:error (validation/move board move))] 
      (do (console/show-error error-str) 
          (recur board _))
      (read-string move))))

; TODO - should I put this into board?
(defn- find-empty-space [board]
  (let [move (rand-nth (range 1 (inc (board/size board))))]
   (if (nil? (board/bget move board))
     move
     (recur board))))

(defmethod get-move :computer [board _]
  (let [move (find-empty-space board)]
    (console/print-computer-move move)
    move))
