export var state = function () { return ({
    people: [],
    debug: false,
    profile: {
        name: '2',
    },
}); };
export var mutations = {
    setPeople: function (state, people) {
        state.people = people;
    },
};
export var actions = {};
