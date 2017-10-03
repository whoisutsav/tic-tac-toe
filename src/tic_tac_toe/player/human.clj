(ns tic-tac-toe.player.human
  (:require [tic-tac-toe.player :refer [get-move]] 
            [tic-tac-toe.console.ui :as console-ui]
            [tic-tac-toe.validation :as validation]))

(defn- invalid-move [error-str]
  (console-ui/print-message error-str)
  (console-ui/get-user-input))

(defn- move-loop [board]
  (loop [move (console-ui/get-user-input)] 
    (if-let [error-str (:error (validation/move board move))]
      (recur (invalid-move error-str))
      (read-string move))))

(defmethod get-move :human [game]
  (console-ui/print-move-prompt)
  (move-loop (:board game)))

