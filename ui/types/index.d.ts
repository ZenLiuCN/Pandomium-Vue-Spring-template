export interface Menu {
    ico: string
    title: string
    to: string
}

export interface IApi {
    name: string
}

export type IApls = { [index: string]: IApi }

export interface IProject {
    id: string
    name: string
    desc: string
    api: IApls | null
    createBy: string[]
    createAt: Date
    viewer: string[]
    editor: string[]
    updateAt: Date,
    childCount: number,
    child?: IProject[] | null
}
