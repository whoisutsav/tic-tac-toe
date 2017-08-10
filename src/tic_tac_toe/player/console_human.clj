(ns tic-tac-toe.player.console-human
  (:require [tic-tac-toe.player :refer :all] 
            [tic-tac-toe.console-interface :as console]
            [tic-tac-toe.validation :as validation]))

(defmethod get-move :human [board _]
  (console/print-move-prompt)
  (let [move (console/get-user-input)] 
    (if-let [error-str (:error (validation/move board move))] 
      (do (console/print-error error-str) 
          (recur board _))
      (read-string move))))
