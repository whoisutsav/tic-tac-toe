(ns tic-tac-toe.web.runner
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.game :as game]
            [tic-tac-toe.decision :as decision]
            [tic-tac-toe.basic-game :as basic-game]
            [tic-tac-toe.player.human-web] ;TODO remove
            [tic-tac-toe.player.hard-computer] ;TODO remove
            [tic-tac-toe.console.player-setup :as player-setup]
            [ring.util.response :refer [response]]))

(def IN_PROGRESS "IN_PROGRESS")
(def WIN "WIN")
(def DRAW "DRAW")

; TODO use get-in?
; TODO not quite "response" - be more clear with words
(defn ttt-response [params] 
  { :status (:status params IN_PROGRESS)
    :board (:board params) 
    :marker (:marker (:current-player params))
    :opponent (:type (:opponent-player params))
    :winner (:winner params)}) 

(defn flag-winner [params winner]
  (-> params 
      (assoc :winner winner)
      (assoc :status WIN))) 

(defn flag-draw [params]
  (assoc params :status DRAW))

; TODO make this simpler - should request format be changed?
(defn map-request [request]
  (let [params (-> (:form-params request))
        board (->> (get params "board")
                   (mapv keyword))
        current-player (->> (get params "marker")
                            (keyword)
                            (player-setup/make-player :human-web))
        opponent-player (-> (get params "opponent")
                            (keyword)
                            (player-setup/make-player :O))
        move (-> (get params "move") (read-string))]
    {:board board
     :current-player current-player
     :opponent-player opponent-player
     :move move
     }))


(defn- end-game [game]
  (if-let [winner (decision/winner (:board game))]
    (-> game (flag-winner winner) (ttt-response))
    (-> game (flag-draw) (ttt-response))))

; TODO split into multiple functions 
(defn move [request]
  (loop [game (map-request request)]
    (let [updated-game (basic-game/take-turn game)
          player-type (:type (:current-player updated-game))]
      (if (decision/over? (:board updated-game)) 
        (end-game updated-game)
        (if (or (= :computer player-type) (= :hard-computer player-type)) 
          (recur updated-game)
          (ttt-response updated-game))))))


(defn new-game [request]
  (let [opponent-type (-> (:form-params request)
                     (get "opponent" :computer)
                     (keyword))
        current-player (player-setup/make-player :human-web :X)
        opponent-player (player-setup/make-player opponent-type :O)
        board (board/new-board 3)] 
    (-> (game/make-game [current-player opponent-player] board)
        (ttt-response))))

