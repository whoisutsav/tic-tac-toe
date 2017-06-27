(ns tic-tac-toe.console-input
  (:require [clojure.string :as str])) 

(defn prompt-for-move [marker]
  (println (str marker ": please enter move."))
  (str/trim (read-line)))



