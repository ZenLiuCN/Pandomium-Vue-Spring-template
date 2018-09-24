export const state = () => ({
    people: [],
    debug: false,
    profile: {
        name: '2',
    },
})

export const mutations = {
    setPeople(state, people) {
        state.people = people
    },
}

export const actions = {
    /*  async nuxtServerInit({ commit }, { app }) {
          const people = await app.$axios.$get(
              "./random-data.json"
          )
          commit("setPeople", people.slice(0, 10))
      }*/
}
