(ns tic-tac-toe.player.human
  (:require [tic-tac-toe.player :refer [get-move]] 
            [tic-tac-toe.console.ui :as console-ui]
            [tic-tac-toe.validation :as validation]))

(defn- invalid-move [error-str]
  (console-ui/print-message error-str)
  (console-ui/get-user-input))

(defn- move-loop [board]
  (loop [move (console-ui/get-user-input)] 
    (let [error-str (:error (validation/move board move))]
      (if (nil? error-str) 
        (read-string move)
        (recur (invalid-move error-str))))))

(defmethod get-move :human [game]
  (console-ui/print-move-prompt)
  (move-loop (:board game)))

