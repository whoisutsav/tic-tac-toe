(ns tic-tac-toe.player
  (:require [tic-tac-toe.console-output :as console-output]
            [tic-tac-toe.validation :as validation]))

(defn get-move [board current-marker markers]
  (console-output/show-move-prompt current-marker markers board)
  (loop [move (console-output/get-user-input)] 
    (if-let [error-str (:error (validation/move board move))] 
      (do (console-output/show-error error-str) 
          (recur (console-output/get-user-input)))
      (read-string move))))


