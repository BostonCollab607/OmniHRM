package main

import (
	"net/http"

	"github.com/go-chi/chi/v5"
	"github.com/go-chi/chi/v5/middleware"
)

func (app *application) routes() http.Handler {
	// create a router mux
	mux := chi.NewRouter()

	mux.Use(middleware.Recoverer)
	mux.Use(app.enableCORS)

	mux.Get("/", app.Home)

	mux.Post("/authenticate", app.authenticate)
	mux.Get("/refresh", app.refreshToken)
	mux.Get("/logout", app.logout)

	mux.Route("/admin", func(mux chi.Router) {
		mux.Use(app.authRequired)
		// Put all the routes and route handlers here.
		mux.Get("/testroutes", app.testRoutes)
		// mux.Get("/movies/{id}", app.MovieForEdit)
		// mux.Put("/movies/0", app.InsertMovie)
		// mux.Patch("/movies/{id}", app.UpdateMovie)
		// mux.Delete("/movies/{id}", app.DeleteMovie)
	})

	return mux
}
