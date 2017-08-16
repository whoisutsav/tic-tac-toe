(ns tic-tac-toe.console-game-setup
  (:require [tic-tac-toe.console-ui :as console-ui]
            [tic-tac-toe.validation :as validation]
            [tic-tac-toe.board :as board])) 

(def options [ "1. Human vs. Human"
               "2. Human vs. Computer"
               "3. Human vs. Hard Computer"
               ])

(defmulti get-marker (fn [player-type is-opponent] player-type))

(defmethod get-marker :human [player-type is-opponent]
  (console-ui/print-marker-prompt is-opponent)
  (let [marker (console-ui/get-user-input)]
    (if-let [error-str (:error (validation/marker marker))] 
      (do (console-ui/print-message error-str) (recur player-type is-opponent))
      (keyword marker))))

(defmethod get-marker :default [_ _]
  (console-ui/print-message "Computer chose marker O") 
  :O)

(defn- configure [player-type opponent-type]
  {
     :board (board/new-board)
     :current-player {:type player-type :marker (get-marker player-type false)}
     :opponent-player {:type opponent-type :marker (get-marker opponent-type true)}})

(defn new-game []
  (console-ui/print-menu options)
  (let [game-type (read-string (console-ui/get-user-input))]
    (case game-type
      1 (configure :human :human)
      2 (configure :human :computer)
      3 (configure :human :hard-computer)
      (do (console-ui/print-message "Bad option") (recur)))))
