(ns tic-tac-toe.console 
  (:require [clojure.string :as str]
            [tic-tac-toe.board :as board]))

(def empty-space "_")

; TODO polish method 
(defn print-game-menu [options]
  (println "Please select the type of game:")
  (loop [options options]
    (when-let [option (first options)]
      (println option)
      (recur (rest options)))))

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

(defn print-turn-message [marker]
  (println (str (name marker) "'s turn")))

(defn print-computer-move [move]
  (println (str "Computer chose space " move)))

(defn print-error [message]
  (println message))

(defn print-marker-prompt [player-num]
  (println (str "Player " player-num ", please enter marker:")))

(defn print-move-prompt []
  (println "Please enter move:"))

(defn declare-winner [marker]
  (println (str (name marker) " wins!")))

(defn declare-draw []
  (println "Cats game."))

(defn get-user-input []
  (str/trim (read-line)))


