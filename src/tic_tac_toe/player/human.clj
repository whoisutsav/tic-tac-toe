(ns tic-tac-toe.player.human
  (:require [tic-tac-toe.player :refer [get-move]] 
            [tic-tac-toe.console-ui :as console-ui]
            [tic-tac-toe.validation :as validation]))

(defmethod get-move :human [board _ _]
  (console-ui/print-move-prompt)
  (let [move (console-ui/get-user-input)
        error-str (:error (validation/move board move)) ] 
    (if (nil? error-str) 
      (read-string move)
      (do (console-ui/print-message error-str) 
          (recur board _ _)))))

