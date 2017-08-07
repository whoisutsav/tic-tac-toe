(ns tic-tac-toe.cli-menu
  (:require [tic-tac-toe.console :as console]
            [tic-tac-toe.human-player :as player])) 

(def human-vs-human "1. Human vs. Human")
(def human-vs-computer "2. Human vs Computer")

(defn human-player [order]
 {:marker (player/get-marker order) :type :human})

(defn computer-player []
  (println "Computer chose marker O")
  {:marker :O :type :computer})

(defn get-options []
  (console/print-game-menu [human-vs-human human-vs-computer])
  (let [choice (read-string (console/get-user-input))]
    (cond 
          (= 1 choice) 
          [(human-player :primary) (human-player :opponent)]
          (= 2 choice)
          [(human-player :primary) (computer-player)]
          :else (recur))))
