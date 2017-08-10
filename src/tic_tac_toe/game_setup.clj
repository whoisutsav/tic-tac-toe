(ns tic-tac-toe.game-setup
  (:require [tic-tac-toe.console :as console]
            [tic-tac-toe.validation :as validation]
            [tic-tac-toe.board :as board])) 

(def options [ "1. Human vs. Human"
               "2. Human vs. Computer"])

(defmulti get-marker (fn [player-type is-opponent] player-type))

(defmethod get-marker :human [player-type is-opponent]
  (console/print-marker-prompt is-opponent)
  (let [marker (console/get-user-input)]
    (if-let [error-str (:error (validation/marker marker))] 
      (do (console/print-error error-str) (recur player-type is-opponent))
      (keyword marker))))

(defmethod get-marker :computer [_ _]
  (println "Computer chose marker O") ; TOOD move this into console?
  :O)

; TODO rename new-game/initialize-game
(defn- initialize-game [player opponent]
  {
     :board (board/new-board)
     :current-player {:type player :marker (get-marker player false)}
     :opponent-player {:type opponent :marker (get-marker opponent true)}})

(defn new-game []
  (console/print-menu options)
  (let [game-type (read-string (read-line))]
    (case game-type
      1 (initialize-game :human :human)
      2 (initialize-game :human :computer)
      (recur))))
