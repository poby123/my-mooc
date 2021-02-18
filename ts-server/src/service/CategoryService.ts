import { Connection, Repository } from "typeorm";
import { Category } from "../entity/Category";
import { Organization } from "../entity/Organization";
import { Member } from '../entity/Member';
import ConnectionProvider from "../provider/ConnectionProvider";

export class CategoryService {
    private categoryRepository: Repository<Category>;
    private static service : CategoryService;

    private constructor() {
        this.categoryRepository = ConnectionProvider.connection().getRepository(Category);
    }

    public static getInstance(){
        if(!CategoryService.service){
            CategoryService.service = new CategoryService();
        }
        return CategoryService.service;
    }

    public async add(organization: Organization, title: string, member?: Member[]) {
        let category = new Category();
        category.organization = organization;
        category.title = title;
        category.member = member ? member : [];
        return await this.categoryRepository.save(category);
    }

    public async getById(id: number) {
        return await this.categoryRepository.findOne(id);
    }

    public async getByOrganization(organization: Organization) {
        return await this.categoryRepository.find({ organization: organization });
    }

    public async delete(category: Category) {
        return await this.categoryRepository.remove(category);
    }

    public async deleteCategories(categories: Category[]) {
        categories && categories.forEach(async (category) => {
            await this.delete(category);
        })
    }
}