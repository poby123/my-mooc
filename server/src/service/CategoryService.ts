import { Connection, Repository } from "typeorm";
import { Category } from "../entity/Category";
import { Organization } from "../entity/Organization";

export class CategoryService {
    private categoryRepository: Repository<Category>;

    constructor(connection: Connection) {
        this.categoryRepository = connection.getRepository(Category);
    }

    public async add(organization: Organization, title: string, role?: JSON) {
        let category = new Category();
        category.organization = organization;
        category.title = title;
        role && (category.role = role);

        return await this.categoryRepository.save(category);
    }

    public async getById(id: number) {
        return await this.categoryRepository.findOne(id);
    }

    public async delete(category: Category) {
        return await this.categoryRepository.remove(category);
    }
}