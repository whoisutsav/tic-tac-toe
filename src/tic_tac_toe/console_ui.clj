(ns tic-tac-toe.console-ui 
  (:require [clojure.string :as str]
            [tic-tac-toe.board :as board]))

;;;;;;;;;; Game Setup ;;;;;;;;;;
(defn print-menu [options]
  (println "Please select the type of game:")
  (loop [options options]
    (when-first [option options]
      (println option)
      (recur (rest options)))))

(defn print-marker-prompt [is-opponent]
  (if is-opponent
    (println "Please enter opponent's marker:") 
    (println "Please enter player's marker:")))

(defn print-computer-marker [computer-marker]
  (println (str "Computer chose marker " (name computer-marker))))


;;;;;;;;;; Board ;;;;;;;;;;
(defn- populate-numbers [board]
      (map 
        #(if-let [marker (board/get-marker % board)] 
           (name marker)
           %) 
        (range 1 (inc (board/size board)))))

(defn- format-row [row]
  (str "\t| " (reduce str "" (interpose " | " row)) " |\n")) 

(defn format-board [board]
  (->> (populate-numbers board)
       (partition 3)
       (map format-row)
       (reduce str)))

(defn print-board [board]
  (println "\n\t-------------")
  (print (format-board board))
  (println "\t-------------\n"))

;;;;;;;;;; Turn ;;;;;;;;;;
(defn print-turn [marker]
  (println (str (name marker) "'s turn")))

(defn print-move [marker move]
  (println (str (name marker) " moves to " move)))

(defn print-move-prompt []
  (println "Please enter move:"))


;;;;;;;;;; Decision ;;;;;;;;;;
(defn declare-winner [marker]
  (println (str (name marker) " wins!")))

(defn declare-draw []
  (println "Cats game."))

;;;;;;;;;; General ;;;;;;;;;;
(defn print-message [message]
  (println message))

(defn get-user-input []
  (str/trim (read-line)))


