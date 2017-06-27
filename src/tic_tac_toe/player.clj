(ns tic-tac-toe.player
  (:require [tic-tac-toe.console-input :as console-input]
            [tic-tac-toe.console-output :as console-output]
            [tic-tac-toe.validation :as validation]))


(defn get-move [board marker]
  (loop [move (console-input/prompt-for-move marker)] 
    (if-let [error-str (:error (validation/move board move))] 
      (do (console-output/print-message error-str) 
          (recur (console-input/prompt-for-move marker)))
      (read-string move))))


