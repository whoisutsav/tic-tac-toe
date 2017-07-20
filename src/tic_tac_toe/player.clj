(ns tic-tac-toe.player
  (:require [tic-tac-toe.console :as console]
            [tic-tac-toe.validation :as validation]))


(defn get-marker [player]
  (console/show-marker-prompt (if (= :primary player) 1 2))
  (let [marker (console/get-user-input)]
    (if-let [error-str (:error (validation/marker marker))]
      (do (console/show-error error-str)
          (recur player))
      marker)))


(defn get-move [board current-marker markers]
  (console/show-move-prompt current-marker markers board)
  (let [move (console/get-user-input)] 
    (if-let [error-str (:error (validation/move board move))] 
      (do (console/show-error error-str) 
          (recur board current-marker markers))
      (read-string move))))


