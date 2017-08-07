(ns tic-tac-toe.human-player
  (:require [tic-tac-toe.player :refer :all] 
            [tic-tac-toe.console :as console]
            [tic-tac-toe.validation :as validation]))

;TODO should not belong here
(defn get-marker [order]
  (console/print-marker-prompt (if (= :primary order) 1 2))
  (let [marker (console/get-user-input)]
    (if-let [error-str (:error (validation/marker marker))]
      (do (console/print-error error-str)
          (recur order))
      (keyword marker))))

(defmethod get-move :human [board _]
  (console/print-move-prompt)
  (let [move (console/get-user-input)] 
    (if-let [error-str (:error (validation/move board move))] 
      (do (console/print-error error-str) 
          (recur board _))
      (read-string move))))

