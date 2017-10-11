(ns tic-tac-toe.web.game-runner
  (:require [tic-tac-toe.core.board :as board]
            [tic-tac-toe.core.decision :as decision]
            [tic-tac-toe.core.basic-game :as basic-game]))

(defn- add-meta [params winner state]
  (-> (merge params {:winner winner :state state})
      (assoc :size (board/size (:board params)))))

(defn- metadata-active [params]
  (add-meta params nil "active"))

(defn- metadata-win [params winner]
  (add-meta params winner "win")) 

(defn- metadata-draw [params]
  (add-meta params nil "draw"))

(defn- end-game [game]
  (if-let [winner (decision/winner (:board game))]
    (metadata-win game winner)
    (metadata-draw game)))

(defn- recur-move [game]
  (declare move)
  (let [current-player (:type (:current-player game))] 
    (if (or (= :computer current-player) (= :hard-computer current-player))
      (move game)
      (metadata-active game))))

(defn move [game]
  (let [updated-game (basic-game/take-turn game)]
      (if (decision/over? (:board updated-game)) 
        (end-game updated-game)
        (recur-move updated-game))))

(defn init [opponent-type size]
  (let [board (board/new-board size)]
    (-> {:board board
         :current-player {:type :human-web :marker :X}
         :opponent-player {:type opponent-type :marker :O}}
        (metadata-active))))

